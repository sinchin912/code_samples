import { HttpException, HttpStatus, Inject, Injectable } from '@nestjs/common';
import { CreateCustomerDto } from './dto/create-customer.dto';
import { UpdateCustomerDto } from './dto/update-customer.dto';
import { Customer } from './entities/customer.entity';
import { CustomersRepository } from './customers.repository';
import { InjectRepository } from '@nestjs/typeorm';
import { Logger } from 'winston';
import { RolesService } from '../roles/roles.service';

@Injectable()
export class CustomersService {
  constructor(
    @InjectRepository(CustomersRepository)
    private readonly customersRepository: CustomersRepository,
    @Inject(RolesService)
    private readonly rolesService: RolesService,
    @Inject('winston')
    private readonly logger: Logger,
  ) {}

  async create(createCustomerDto: CreateCustomerDto): Promise<Customer> {
    this.logger.error('Custom error level log at save of customer', {
      param1: 'CustomerService',
      param2: 'create',
    });
    const newCustomer = this.customersRepository.create(createCustomerDto);
    newCustomer.userkey = '{noop}' + newCustomer.userkey;

    const rolePromises = createCustomerDto.roleIds.map(async (roleId) => {
      return await this.rolesService.findOne(roleId);
    });

    const roles = await Promise.all(rolePromises);

    newCustomer.roles = roles;
    return await this.customersRepository.save(newCustomer);
  }

  async findAll(): Promise<Customer[]> {
    this.logger.debug('Custom debug level log at find all customers', {
      param1: 'CustomerService',
      param2: 'findAll',
    });
    return await this.customersRepository.find();
  }

  async findOne(id: number): Promise<Customer> {
    const customer = await this.customersRepository.findOneBy({ id });
    if (!customer) {
      throw new HttpException(
        `Customer with ID ${id} not found`,
        HttpStatus.BAD_REQUEST,
      );
    }
    return customer;
  }

  async update(
    id: number,
    updateCustomerDto: UpdateCustomerDto,
  ): Promise<Customer> {
    this.logger.warn('Custom warn level log at update of customer', {
      param1: 'CustomerService',
      param2: 'update',
    });
    const customer = await this.findOne(id);
    this.customersRepository.merge(customer, updateCustomerDto);
    return await this.customersRepository.save(customer);
  }

  async remove(id: number): Promise<void> {
    this.logger.info('Custom info level log at delete of customer', {
      param1: 'CustomerService',
      param2: 'remove',
    });
    const customer = await this.findOne(id);
    await this.customersRepository.remove(customer);
  }

  async findByUsername(username: string): Promise<Customer | undefined> {
    return await this.customersRepository.findByUsername(username);
  }

  async findRolesByUsername(username: string): Promise<string[]> {
    return await this.customersRepository.findRolesByUsername(username);
  }
}
