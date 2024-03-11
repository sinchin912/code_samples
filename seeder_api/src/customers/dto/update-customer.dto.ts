import { PartialType } from '@nestjs/mapped-types';
import { CreateCustomerDto } from './create-customer.dto';
import { ApiProperty } from '@nestjs/swagger';

export class UpdateCustomerDto extends PartialType(CreateCustomerDto) {
  @ApiProperty()
  name: string;
  @ApiProperty()
  username: string;
  @ApiProperty()
  userkey: string;
}
