import { ArgumentsHost, HttpException, HttpStatus } from '@nestjs/common';
import {
  DbExceptionFilter,
  GlobalExceptionFilter,
  HttpExceptionFilter,
} from './http-exception.filter';
import { QueryFailedError } from 'typeorm';

describe('HttpExceptionFilter', () => {
  let httpExceptionFilter: HttpExceptionFilter;
  let dbExceptionFilter: DbExceptionFilter;
  let globalExceptionFilter: GlobalExceptionFilter;
  beforeEach(() => {
    httpExceptionFilter = new HttpExceptionFilter();
    dbExceptionFilter = new DbExceptionFilter();
    globalExceptionFilter = new GlobalExceptionFilter();
  });

  it('should catch and handle HttpException', () => {
    const mockStatus = 404;
    const mockMessage = 'Not Found';
    const mockException = new HttpException(mockMessage, mockStatus);
    const mockResponse = {
      status: jest.fn().mockReturnThis(),
      json: jest.fn(),
    };
    const mockHost: ArgumentsHost = {
      switchToHttp: jest.fn().mockReturnValue({
        getRequest: jest.fn().mockReturnValue({ url: 'test-url' }),
        getResponse: jest.fn().mockReturnValue(mockResponse),
      }),
      getArgs: jest.fn(),
      getArgByIndex: jest.fn(),
      switchToRpc: jest.fn(),
      switchToWs: jest.fn(),
      getType: jest.fn(),
    };

    httpExceptionFilter.catch(mockException, mockHost);

    expect(mockResponse.status).toHaveBeenCalledWith(mockStatus);
    expect(mockResponse.json).toHaveBeenCalledWith({
      statusCode: mockStatus,
      timestamp: expect.any(String),
      path: 'test-url',
      message: mockMessage,
    });
  });

  it('should catch and handle QueryFailedError', () => {
    const errorMessage = 'Mocked QueryFailedError';
    const mockException = new QueryFailedError(errorMessage, [], new Error());
    const mockResponse = {
      status: jest.fn().mockReturnThis(),
      json: jest.fn(),
    };
    const mockHost: ArgumentsHost = {
      switchToHttp: jest.fn().mockReturnValue({
        getRequest: jest.fn().mockReturnValue({ url: 'test-url' }),
        getResponse: jest.fn().mockReturnValue(mockResponse),
      }),
      getArgs: jest.fn(),
      getArgByIndex: jest.fn(),
      switchToRpc: jest.fn(),
      switchToWs: jest.fn(),
      getType: jest.fn(),
    };

    dbExceptionFilter.catch(mockException, mockHost);

    expect(mockResponse.status).toHaveBeenCalledWith(HttpStatus.BAD_REQUEST);
  });

  it('should catch and handle GlobalException', () => {
    const mockException = new Error('Internal Server Error');
    const mockResponse = {
      status: jest.fn().mockReturnThis(),
      json: jest.fn(),
    };
    const mockHost: ArgumentsHost = {
      switchToHttp: jest.fn().mockReturnValue({
        getRequest: jest.fn().mockReturnValue({ url: 'test-url' }),
        getResponse: jest.fn().mockReturnValue(mockResponse),
      }),
      getArgs: jest.fn(),
      getArgByIndex: jest.fn(),
      switchToRpc: jest.fn(),
      switchToWs: jest.fn(),
      getType: jest.fn(),
    };

    globalExceptionFilter.catch(mockException, mockHost);

    expect(mockResponse.status).toHaveBeenCalledWith(
      HttpStatus.INTERNAL_SERVER_ERROR,
    );
    expect(mockResponse.json).toHaveBeenCalledWith({
      statusCode: HttpStatus.INTERNAL_SERVER_ERROR,
      timestamp: expect.any(String),
      path: 'test-url',
      message: 'Internal Server Error',
      error: mockException.stack,
    });
  });
});
