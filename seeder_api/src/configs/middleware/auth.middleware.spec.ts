import { Test, TestingModule } from '@nestjs/testing';
import { AuthMiddleware } from './auth.middleware';
import { AuthService } from '../auth.service';
import { HttpException, HttpStatus } from '@nestjs/common';
import { Request, Response } from 'express';

describe('AuthMiddleware', () => {
  let middleware: AuthMiddleware;
  let authService: AuthService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [
        AuthMiddleware,
        {
          provide: AuthService,
          useValue: {
            signIn: jest.fn(),
          },
        },
      ],
    }).compile();

    middleware = module.get<AuthMiddleware>(AuthMiddleware);
    authService = module.get<AuthService>(AuthService);
  });

  it('should be defined', () => {
    expect(middleware).toBeDefined();
    jest.restoreAllMocks();
  });

  it('should throw HttpException if auth header is not present', async () => {
    const req = { headers: {} } as Request;
    const res = {} as Response;
    const next = jest.fn();
    const error = new HttpException(
      'Auth Header not present',
      HttpStatus.FORBIDDEN,
    );
    await middleware.use(req, res, next);
    expect(next).toHaveBeenCalledWith(error);
  });

  it('should throw HttpException if auth header is invalid', async () => {
    const req = { headers: { authorization: 'InvalidHeader' } } as Request;
    const res = {} as Response;
    const next = jest.fn();
    const error = new HttpException(
      'Invalid Auth Header',
      HttpStatus.FORBIDDEN,
    );
    await middleware.use(req, res, next);
    expect(next).toHaveBeenCalledWith(error);
  });

  it('should return logged in customer', async () => {
    const req = {
      headers: { authorization: 'Basic YWRtaW46e25vb3B9YWRtaW4=' },
    } as Request;
    const res = {} as Response;
    const next = jest.fn();
    const mockCustomer = {
      id: 1,
      name: 'Customer 1',
      username: 'user1',
      userkey: 'password1',
      active: true,
      createTimestamp: new Date(),
      createdBy: 1,
      updateTimestamp: new Date(),
      updatedBy: 1,
    };
    jest.spyOn(authService, 'signIn').mockResolvedValue(mockCustomer);
    expect(await middleware.use(req, res, next)).resolves;
    expect(next).toHaveBeenCalled();
    jest.restoreAllMocks();
  });
});
