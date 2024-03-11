import {
  Controller,
  Get,
  Post,
  Body,
  Patch,
  Param,
  Delete,
  UseGuards,
  ValidationPipe,
  ParseIntPipe,
  HttpStatus,
} from '@nestjs/common';
import { CustomersService } from './customers.service';
import { CreateCustomerDto } from './dto/create-customer.dto';
import { UpdateCustomerDto } from './dto/update-customer.dto';
import { ApiTags } from '@nestjs/swagger';

import { AdminRoleGuard } from '../configs/guard/admin-role/admin-role.guard';
import { PostRequestGuard } from '../configs/guard/post-request/post-request.guard';
import { UpdateRequestGuard } from '../configs/guard/update-request/update-request.guard';
import { AnyRoleGuard } from '../configs/guard/any-role/any-role.guard';
import { CustomerValidationPipe } from './validator/customers.validator-pipe';

@ApiTags('customers')
@Controller('customers')
export class CustomersController {
  constructor(private readonly customersService: CustomersService) {}

  @Post()
  @UseGuards(AdminRoleGuard)
  @UseGuards(PostRequestGuard)
  create(
    @Body(ValidationPipe, new CustomerValidationPipe())
    createCustomerDto: CreateCustomerDto,
  ) {
    return this.customersService.create(createCustomerDto);
  }

  @Get()
  @UseGuards(AnyRoleGuard)
  findAll() {
    return this.customersService.findAll();
  }

  @Get(':id')
  @UseGuards(AnyRoleGuard)
  findOne(
    @Param(
      'id',
      new ParseIntPipe({ errorHttpStatusCode: HttpStatus.NOT_ACCEPTABLE }),
    )
    id: number,
  ) {
    return this.customersService.findOne(id);
  }

  @Patch(':id')
  @UseGuards(AdminRoleGuard)
  @UseGuards(UpdateRequestGuard)
  update(
    @Param('id') id: string,
    @Body() updateCustomerDto: UpdateCustomerDto,
  ) {
    return this.customersService.update(+id, updateCustomerDto);
  }

  @Delete(':id')
  @UseGuards(AdminRoleGuard)
  remove(@Param('id') id: string) {
    return this.customersService.remove(+id);
  }
}
