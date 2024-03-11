import { Test, TestingModule } from '@nestjs/testing';
import { CustomersController } from './customers.controller';
import { CustomersService } from './customers.service';
import { AnyRoleGuard } from '../configs/guard/any-role/any-role.guard';
import { AdminRoleGuard } from '../configs/guard/admin-role/admin-role.guard';
import { CustomerRoleGuard } from '../configs/guard/customer-role/customer-role.guard';
import { HttpException } from '@nestjs/common';

describe('CustomersController', () => {
  let controller: CustomersController;
  let service: CustomersService;
  let anyRoleGuard: AnyRoleGuard;
  let adminRoleGuard: AdminRoleGuard;
  let customerRoleGuard: CustomerRoleGuard;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [CustomersController],
      providers: [
        {
          provide: CustomersService,
          useValue: {
            findAll: jest.fn(),
            create: jest.fn(),
            findOne: jest.fn(),
            update: jest.fn(),
            remove: jest.fn(),
          },
        },
        {
          provide: AnyRoleGuard,
          useValue: {
            canActivate: jest.fn(),
          },
        },
        {
          provide: AdminRoleGuard,
          useValue: {
            canActivate: jest.fn(),
          },
        },
        {
          provide: CustomerRoleGuard,
          useValue: {
            canActivate: jest.fn(),
          },
        },
      ],
    }).compile();

    controller = module.get<CustomersController>(CustomersController);
    service = module.get<CustomersService>(CustomersService);
    adminRoleGuard = module.get<AdminRoleGuard>(AdminRoleGuard);
    customerRoleGuard = module.get<CustomerRoleGuard>(CustomerRoleGuard);
    anyRoleGuard = module.get<AnyRoleGuard>(AnyRoleGuard);
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });

  it('should throw http exception', async () => {
    jest.spyOn(adminRoleGuard, 'canActivate').mockReturnValue(false);
    jest.spyOn(customerRoleGuard, 'canActivate').mockReturnValue(false);
    jest.spyOn(anyRoleGuard, 'canActivate').mockReturnValue(false);
    try {
      await controller.findOne(1);
    } catch (error) {
      expect(error).toBeInstanceOf(HttpException);
    }
  });

  it('should return single customer', async () => {
    const mockCustomer = {
      id: 1,
      name: 'Customer 1',
      username: 'user1',
      userkey: 'password1',
      active: true,
      createTimestamp: new Date(),
      createdBy: 1,
      updateTimestamp: new Date(),
      updatedBy: 1,
    };
    jest.spyOn(service, 'findOne').mockResolvedValue(mockCustomer);
    expect(await controller.findOne(1)).toEqual(mockCustomer);
  });

  it('should return created customer', async () => {
    const postCustomer = {
      name: 'Customer 1',
      username: 'user1',
      userkey: 'password1',
      roleIds: [1],
    };
    const mockCustomer = {
      id: 1,
      name: 'Customer 1',
      username: 'user1',
      userkey: 'password1',
      active: true,
      createTimestamp: new Date(),
      createdBy: 1,
      updateTimestamp: new Date(),
      updatedBy: 1,
    };
    jest.spyOn(service, 'create').mockResolvedValue(mockCustomer);
    expect(await controller.create(postCustomer)).toEqual(mockCustomer);
  });

  it('should return updated customer', async () => {
    const postCustomer = {
      name: 'Customer 1',
      username: 'user1',
      userkey: 'password1',
    };
    const mockCustomer = {
      id: 1,
      name: 'Customer 1',
      username: 'user1',
      userkey: 'password1',
      active: true,
      createTimestamp: new Date(),
      createdBy: 1,
      updateTimestamp: new Date(),
      updatedBy: 1,
    };
    jest.spyOn(service, 'update').mockResolvedValue(mockCustomer);
    expect(await controller.update('1', postCustomer)).toEqual(mockCustomer);
  });

  it('should return void', async () => {
    const removeSpy = jest
      .spyOn(service, 'remove')
      .mockResolvedValueOnce(undefined);
    await controller.remove('1');
    expect(removeSpy).toHaveBeenCalledWith(1);
  });

  it('should return all customers', async () => {
    const mockCustomers = [
      {
        id: 1,
        name: 'Customer 1',
        username: 'user1',
        userkey: 'password1',
        active: true,
        createTimestamp: new Date(),
        createdBy: 1,
        updateTimestamp: new Date(),
        updatedBy: 1,
      },
      {
        id: 2,
        name: 'Customer 2',
        username: 'user2',
        userkey: 'password2',
        active: true,
        createTimestamp: new Date(),
        createdBy: 1,
        updateTimestamp: new Date(),
        updatedBy: 1,
      },
    ];
    jest.spyOn(service, 'findAll').mockResolvedValue(mockCustomers);

    expect(await controller.findAll()).toEqual(mockCustomers);
  });
});
