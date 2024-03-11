import { Module, forwardRef } from '@nestjs/common';
import { AuthService } from './auth.service';
import { AuthMiddleware } from './middleware/auth.middleware';
import { CustomersModule } from 'src/customers/customers.module';
import { AdminRoleGuard } from './guard/admin-role/admin-role.guard';
import { CustomerRoleGuard } from './guard/customer-role/customer-role.guard';
import { PostRequestGuard } from './guard/post-request/post-request.guard';
import { UpdateRequestGuard } from './guard/update-request/update-request.guard';
import { AnyRoleGuard } from './guard/any-role/any-role.guard';

@Module({
  imports: [forwardRef(() => CustomersModule)],
  providers: [
    AuthMiddleware,
    AuthService,
    AdminRoleGuard,
    CustomerRoleGuard,
    PostRequestGuard,
    UpdateRequestGuard,
    AnyRoleGuard,
  ],
  exports: [
    AdminRoleGuard,
    CustomerRoleGuard,
    PostRequestGuard,
    UpdateRequestGuard,
    AnyRoleGuard,
  ],
})
export class ConfigsModule {}
