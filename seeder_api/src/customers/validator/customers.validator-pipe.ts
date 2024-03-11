import { PipeTransform, Injectable, HttpException } from '@nestjs/common';
import { CreateCustomerDto } from '../dto/create-customer.dto';

@Injectable()
export class CustomerValidationPipe implements PipeTransform {
  async transform(value: CreateCustomerDto) {
    const { name, username, userkey, roleIds } = value;
    let valid = true;
    const errors: string[] = [];
    if (name === undefined || name === '' || name.length > 50) {
      valid = false;
      errors.push('Name is required with max 50 characters');
    }
    if (username === undefined || username === '' || username.length > 50) {
      valid = false;
      errors.push('Username is required with max 50 characters');
    }
    if (userkey === undefined || userkey === '' || userkey.length > 90) {
      valid = false;
      errors.push('Userkey is required with max 90 characters');
    }
    if (roleIds === undefined || roleIds.length === 0) {
      valid = false;
      errors.push('At least a role is required');
    }

    if (!valid) throw new HttpException(errors.toString(), 400);
    else return value;
  }
}
