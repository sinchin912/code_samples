import { CanActivate, ExecutionContext, Injectable } from '@nestjs/common';
import { Role } from 'src/roles/entities/role.entity';

@Injectable()
export class AdminRoleGuard implements CanActivate {
  canActivate(context: ExecutionContext): boolean {
    const user = context.switchToHttp().getRequest().user;
    const userRoles: string[] = user.roles.map((role: Role) => role.title);
    return userRoles.indexOf('ROLE_ADMIN') > -1;
  }
}
