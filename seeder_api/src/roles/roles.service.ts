import {
  BeforeApplicationShutdown,
  HttpException,
  HttpStatus,
  Injectable,
  OnApplicationBootstrap,
  OnApplicationShutdown,
  OnModuleDestroy,
  OnModuleInit,
} from '@nestjs/common';
import { CreateRoleDto } from './dto/create-role.dto';
import { UpdateRoleDto } from './dto/update-role.dto';
import { Role } from './entities/role.entity';
import { InjectRepository } from '@nestjs/typeorm';
import { RolesRepository } from './roles.repository';

@Injectable()
export class RolesService
  implements
    OnApplicationBootstrap,
    OnModuleInit,
    OnModuleDestroy,
    OnApplicationShutdown,
    BeforeApplicationShutdown
{
  onModuleInit() {
    console.log('RolesService onModuleInit');
  }
  onApplicationBootstrap() {
    console.log('RolesService onApplicationBootstrap');
  }
  onModuleDestroy() {
    console.log('RolesService onModuleDestroy');
  }
  beforeApplicationShutdown(signal?: string | undefined) {
    console.log(`RolesService beforeApplicationShutdown ${signal}`);
  }
  onApplicationShutdown(signal?: string | undefined) {
    console.log(`RolesService onApplicationShutdown ${signal}`);
  }
  constructor(
    @InjectRepository(RolesRepository)
    private readonly rolesRepository: RolesRepository,
  ) {}

  async create(createRoleDto: CreateRoleDto): Promise<Role> {
    const newRole = this.rolesRepository.create(createRoleDto);
    return await this.rolesRepository.save(newRole);
  }

  async findAll(): Promise<Role[]> {
    return await this.rolesRepository.find();
  }

  async findOne(id: number): Promise<Role> {
    const role = await this.rolesRepository.findOneBy({ id });
    if (!role) {
      throw new HttpException(
        `Role with ID ${id} not found`,
        HttpStatus.BAD_REQUEST,
      );
    }
    return role;
  }

  async update(id: number, updateRoleDto: UpdateRoleDto): Promise<Role> {
    const role = await this.findOne(id);
    this.rolesRepository.merge(role, updateRoleDto);
    return await this.rolesRepository.save(role);
  }

  async remove(id: number): Promise<void> {
    await this.rolesRepository.delete(id);
  }
}
