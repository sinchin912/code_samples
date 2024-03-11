import { ExecutionContext } from '@nestjs/common';
import { Test, TestingModule } from '@nestjs/testing';
import { PostRequestGuard } from './post-request.guard';

describe('PostRequestGuard', () => {
  let guard: PostRequestGuard;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [PostRequestGuard],
    }).compile();

    guard = module.get<PostRequestGuard>(PostRequestGuard);
  });

  it('should modify request body as expected', () => {
    const mockUser = { id: 1 };
    const mockBody = { active: null, createdBy: null, createTimestamp: null };

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
    expect(mockBody.active).toBe(true);
    expect(mockBody.createdBy).toBe(1);
    expect(mockBody.createTimestamp).toBeInstanceOf(Date);
  });
});
