import { ExecutionContext } from '@nestjs/common';
import { Test, TestingModule } from '@nestjs/testing';
import { UpdateRequestGuard } from './update-request.guard';

describe('UpdateRequestGuard', () => {
  let guard: UpdateRequestGuard;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [UpdateRequestGuard],
    }).compile();

    guard = module.get<UpdateRequestGuard>(UpdateRequestGuard);
  });

  it('should modify request body as expected', () => {
    const mockUser = { id: 1 };
    const mockBody = { updatedBy: null, updateTimestamp: null };

    const mockRequest: any = {
      user: mockUser,
      body: mockBody,
    };

    const mockContext: ExecutionContext = {
      switchToHttp: jest.fn().mockReturnValue({
        getRequest: jest.fn().mockReturnValue(mockRequest),
      }),
      getClass: jest.fn(),
      getHandler: jest.fn(),
      getArgs: jest.fn(),
      getArgByIndex: jest.fn(),
      getType: jest.fn(),
      switchToRpc: jest.fn(),
      switchToWs: jest.fn(),
    };

    const result = guard.canActivate(mockContext);

    expect(result).toBe(true);
    expect(mockBody.updatedBy).toBe(1);
    expect(mockBody.updateTimestamp).toBeInstanceOf(Date);
  });
});
