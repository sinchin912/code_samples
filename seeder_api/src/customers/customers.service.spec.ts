import { Test, TestingModule } from '@nestjs/testing';
import { CustomersService } from './customers.service';
import { CustomersRepository } from './customers.repository';
import { WinstonModule } from 'nest-winston';
import * as winston from 'winston';
import { RolesService } from '../roles/roles.service';

describe('CustomersService', () => {
  let service: CustomersService;
  let customersRepository: CustomersRepository;
  let roleService: RolesService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      imports: [
        WinstonModule.forRoot({
          transports: [new winston.transports.Console()],
        }),
      ],
      providers: [
        CustomersService,
        {
          provide: CustomersRepository,
          useValue: {
            findByUsername: jest.fn(),
            findRolesByUsername: jest.fn(),
          },
        },
        {
          provide: RolesService,
          useValue: {
            findOne: jest.fn(),
          },
        },
      ],
    }).compile();

    service = module.get<CustomersService>(CustomersService);
    customersRepository = module.get<CustomersRepository>(CustomersRepository);
    roleService = module.get<RolesService>(RolesService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
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
    jest
      .spyOn(customersRepository, 'findByUsername')
      .mockResolvedValue(mockCustomer);
    expect(await service.findByUsername('user1')).toEqual(mockCustomer);
  });

  it('should return string array', async () => {
    const mockRoles = ['Admin', 'User'];
    jest
      .spyOn(customersRepository, 'findRolesByUsername')
      .mockResolvedValue(mockRoles);
    expect(await service.findRolesByUsername('user1')).toEqual(mockRoles);
  });
});
