import { ExecutionContext } from '@nestjs/common';
import { Test, TestingModule } from '@nestjs/testing';
import { AdminRoleGuard } from './admin-role.guard';
import { AuthenticatedRequest } from 'src/commons/interfaces/authenticate-request.interface';

describe('AdminRoleGuard', () => {
  let guard: AdminRoleGuard;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [AdminRoleGuard],
    }).compile();

    guard = module.get<AdminRoleGuard>(AdminRoleGuard);
  });

  it('should return true if user has admin role', () => {
    const mockUser = {
      id: 1,
      name: 'Customer 1',
      username: 'user1',
      userkey: 'password1',
      active: true,
      createTimestamp: new Date(),
      createdBy: 1,
      updateTimestamp: new Date(),
      updatedBy: 1,
      roles: [
        {
          id: 1,
          title: 'ROLE_ADMIN',
          description: '',
          active: true,
          createTimestamp: new Date(),
          createdBy: 1,
          updateTimestamp: new Date(),
          updatedBy: 1,
        },
      ],
    };
    const mockRequest: Partial<AuthenticatedRequest> = { user: mockUser };
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
    expect(guard.canActivate(mockContext)).toBe(true);
  });

  it('should return false if user does not have admin role', () => {
    const mockUser = {
      id: 1,
      name: 'Customer 1',
      username: 'user1',
      userkey: 'password1',
      active: true,
      createTimestamp: new Date(),
      createdBy: 1,
      updateTimestamp: new Date(),
      updatedBy: 1,
      roles: [
        {
          id: 2,
          title: 'ROLE_CUSTOMER',
          description: '',
          active: true,
          createTimestamp: new Date(),
          createdBy: 1,
          updateTimestamp: new Date(),
          updatedBy: 1,
        },
      ],
    };
    const mockRequest: Partial<AuthenticatedRequest> = { user: mockUser };
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
    expect(guard.canActivate(mockContext)).toBe(false);
  });
});
