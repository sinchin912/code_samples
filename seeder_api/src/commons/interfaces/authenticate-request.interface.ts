import { Request } from 'express';
import { Customer } from 'src/customers/entities/customer.entity';

export interface AuthenticatedRequest extends Request {
  user?: Customer;
}
