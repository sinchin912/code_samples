import { Customer } from '../../customers/entities/customer.entity';
import { Generic } from '../../commons/entities/commons.entity';
import { Column, Entity, ManyToMany } from 'typeorm';

@Entity()
export class Role extends Generic {
  @Column({ name: 'title', nullable: false, length: 50 })
  title: string;

  @Column({ name: 'description', nullable: false, length: 50 })
  description: string;

  @ManyToMany(() => Customer, (customer) => customer.roles)
  customers?: Customer[];
}
