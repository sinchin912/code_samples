import { DataSource, Repository } from 'typeorm';
import { Customer } from './entities/customer.entity';
import { Injectable } from '@nestjs/common';

@Injectable()
export class CustomersRepository extends Repository<Customer> {
  constructor(private dataSource: DataSource) {
    super(Customer, dataSource.createEntityManager());
  }

  async findByUsername(username: string): Promise<Customer | undefined> {
    const customers = await this.find({
      where: { username },
      relations: {
        roles: true,
      },
    });
    return customers.at(0);
  }

  async findRolesByUsername(username: string): Promise<string[]> {
    const query = `
      SELECT role.title
      FROM customer
      LEFT OUTER JOIN customer_role ON customer.id = customer_role.customer_id
      LEFT OUTER JOIN role ON customer_role.role_id = role.id
      WHERE customer.username = ?
    `;
    return await this.manager.connection.query(query, [username]);
  }
}
