import { CanActivate, ExecutionContext, Injectable } from '@nestjs/common';
import { AdminRoleGuard } from '../admin-role/admin-role.guard';
import { CustomerRoleGuard } from '../customer-role/customer-role.guard';

@Injectable()
export class AnyRoleGuard implements CanActivate {
  constructor(
    private readonly adminRoleGuard: AdminRoleGuard,
    private readonly customerRoleGuard: CustomerRoleGuard,
  ) {}
  canActivate(context: ExecutionContext): boolean {
    const [resultA, resultB] = [
      this.adminRoleGuard.canActivate(context),
      this.customerRoleGuard.canActivate(context),
    ];
    return resultA || resultB;
  }
}
