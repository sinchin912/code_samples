import { Role } from '../../roles/entities/role.entity';
import { Generic } from '../../commons/entities/commons.entity';
import { Column, Entity, JoinTable, ManyToMany } from 'typeorm';

@Entity()
export class Customer extends Generic {
  @Column({ name: 'name', nullable: false, length: 50 })
  name: string;

  @Column({ name: 'username', nullable: false, length: 50, unique: true })
  username: string;

  @Column({ name: 'userkey', nullable: false, length: 100 })
  userkey: string;

  @ManyToMany(() => Role, (role) => role.customers, {
    cascade: ['insert', 'update', 'soft-remove', 'recover'], // no remove
  })
  @JoinTable({
    name: 'customer_role',
    joinColumn: {
      name: 'customer_id',
    },
    inverseJoinColumn: {
      name: 'role_id',
    },
  })
  roles?: Role[];
}
