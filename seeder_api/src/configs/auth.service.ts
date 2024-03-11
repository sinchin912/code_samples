import { HttpException, HttpStatus, Injectable } from '@nestjs/common';
import { CustomersService } from '../customers/customers.service';
import { Customer } from 'src/customers/entities/customer.entity';

@Injectable()
export class AuthService {
  constructor(private readonly customersService: CustomersService) {}

  async signIn(username: string, pass: string): Promise<Customer> {
    const user = await this.customersService.findByUsername(username);
    if (user?.userkey !== pass) {
      throw new HttpException('Invalid Credentials', HttpStatus.FORBIDDEN);
    }
    if (!user || !user.roles) {
      throw new HttpException(
        'Requires elevated permission',
        HttpStatus.FORBIDDEN,
      );
    }
    return user;
  }
}
