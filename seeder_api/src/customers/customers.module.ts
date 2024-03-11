import { Module, forwardRef } from '@nestjs/common';
import { CustomersService } from './customers.service';
import { CustomersController } from './customers.controller';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Customer } from './entities/customer.entity';
import { CustomersRepository } from './customers.repository';
import { ConfigsModule } from '../configs/configs.module';
import { RolesModule } from '../roles/roles.module';

@Module({
  imports: [
    TypeOrmModule.forFeature([Customer]),
    forwardRef(() => ConfigsModule),
    RolesModule,
  ],
  controllers: [CustomersController],
  providers: [CustomersService, CustomersRepository],
  exports: [CustomersService],
})
export class CustomersModule {}
