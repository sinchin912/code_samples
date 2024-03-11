import { CanActivate, ExecutionContext, Injectable } from '@nestjs/common';
import { Role } from 'src/roles/entities/role.entity';

@Injectable()
export class CustomerRoleGuard implements CanActivate {
  canActivate(context: ExecutionContext): boolean {
    const user = context.switchToHttp().getRequest().user;
    const userRoles: string[] = user.roles.map((role: Role) => role.title);
    return userRoles.indexOf('ROLE_CUSTOMER') > -1;
  }
}
