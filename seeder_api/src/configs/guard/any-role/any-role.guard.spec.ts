import { ExecutionContext } from '@nestjs/common';
import { Test, TestingModule } from '@nestjs/testing';
import { AnyRoleGuard } from './any-role.guard';
import { AdminRoleGuard } from '../admin-role/admin-role.guard';
import { CustomerRoleGuard } from '../customer-role/customer-role.guard';

describe('AnyRoleGuard', () => {
  let guard: AnyRoleGuard;
  let adminRoleGuard: AdminRoleGuard;
  let customerRoleGuard: CustomerRoleGuard;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [
        AnyRoleGuard,
        { provide: AdminRoleGuard, useValue: { canActivate: jest.fn() } },
        { provide: CustomerRoleGuard, useValue: { canActivate: jest.fn() } },
      ],
    }).compile();

    guard = module.get<AnyRoleGuard>(AnyRoleGuard);
    adminRoleGuard = module.get<AdminRoleGuard>(AdminRoleGuard);
    customerRoleGuard = module.get<CustomerRoleGuard>(CustomerRoleGuard);
  });

  it('should return true if either admin or customer role guard returns true', async () => {
    const mockContext: ExecutionContext = {} as ExecutionContext;
    jest.spyOn(adminRoleGuard, 'canActivate').mockReturnValueOnce(true);
    jest.spyOn(customerRoleGuard, 'canActivate').mockReturnValueOnce(false);

    expect(guard.canActivate(mockContext)).toBe(true);
  });

  it('should return true if both admin and customer role guards return true', async () => {
    const mockContext: ExecutionContext = {} as ExecutionContext;
    jest.spyOn(adminRoleGuard, 'canActivate').mockReturnValueOnce(true);
    jest.spyOn(customerRoleGuard, 'canActivate').mockReturnValueOnce(true);

    expect(guard.canActivate(mockContext)).toBe(true);
  });

  it('should return false if both admin and customer role guards return false', async () => {
    const mockContext: ExecutionContext = {} as ExecutionContext;
    jest.spyOn(adminRoleGuard, 'canActivate').mockReturnValueOnce(false);
    jest.spyOn(customerRoleGuard, 'canActivate').mockReturnValueOnce(false);
    expect(guard.canActivate(mockContext)).toBe(false);
  });
});
