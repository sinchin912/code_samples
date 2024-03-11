import { ApiProperty } from '@nestjs/swagger';

export class CreateCustomerDto {
  @ApiProperty()
  name: string;
  @ApiProperty()
  username: string;
  @ApiProperty()
  userkey: string;
  @ApiProperty({ type: Number, isArray: true })
  roleIds: number[];
}
