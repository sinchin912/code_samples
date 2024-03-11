import { Test, TestingModule } from '@nestjs/testing';
import { RolesController } from './roles.controller';
import { RolesService } from './roles.service';

describe('RolesController', () => {
  let controller: RolesController;
  let service: RolesService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [RolesController],
      providers: [
        {
          provide: RolesService,
          useValue: {
            findAll: jest.fn(),
            create: jest.fn(),
            findOne: jest.fn(),
            update: jest.fn(),
            remove: jest.fn(),
          },
        },
      ],
    }).compile();

    controller = module.get<RolesController>(RolesController);
    service = module.get<RolesService>(RolesService);
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });

  it('should return single role', async () => {
    const mockRole = {
      id: 1,
      title: 'Admin',
      description: 'role for config apis',
      active: true,
      createTimestamp: new Date(),
      createdBy: 1,
      updateTimestamp: new Date(),
      updatedBy: 1,
    };
    jest.spyOn(service, 'findOne').mockResolvedValue(mockRole);
    expect(await controller.findOne('1')).toEqual(mockRole);
  });

  it('should return created role', async () => {
    const postRole = {
      title: 'Admin',
      description: 'role for config apis',
    };
    const mockRole = {
      id: 1,
      title: 'Admin',
      description: 'role for config apis',
      active: true,
      createTimestamp: new Date(),
      createdBy: 1,
      updateTimestamp: new Date(),
      updatedBy: 1,
    };
    jest.spyOn(service, 'create').mockResolvedValue(mockRole);
    expect(await controller.create(postRole)).toEqual(mockRole);
  });

  it('should return updated role', async () => {
    const postRole = {
      title: 'Admin',
      description: 'role for config apis',
    };
    const mockRole = {
      id: 1,
      title: 'Admin',
      description: 'role for config apis',
      active: true,
      createTimestamp: new Date(),
      createdBy: 1,
      updateTimestamp: new Date(),
      updatedBy: 1,
    };
    jest.spyOn(service, 'update').mockResolvedValue(mockRole);
    expect(await controller.update('1', postRole)).toEqual(mockRole);
  });

  it('should return void', async () => {
    const removeSpy = jest
      .spyOn(service, 'remove')
      .mockResolvedValueOnce(undefined);
    await controller.remove('1');
    expect(removeSpy).toHaveBeenCalledWith(1);
  });

  it('should return all roles', async () => {
    const mockRoles = [
      {
        id: 1,
        title: 'Admin',
        description: 'role for config apis',
        active: true,
        createTimestamp: new Date(),
        createdBy: 1,
        updateTimestamp: new Date(),
        updatedBy: 1,
      },
      {
        id: 2,
        title: 'User',
        description: 'role for regular apis',
        active: true,
        createTimestamp: new Date(),
        createdBy: 1,
        updateTimestamp: new Date(),
        updatedBy: 1,
      },
    ];
    jest.spyOn(service, 'findAll').mockResolvedValue(mockRoles);

    expect(await controller.findAll()).toEqual(mockRoles);
  });
});
