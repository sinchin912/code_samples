import { Column, PrimaryGeneratedColumn } from 'typeorm';

export class Generic {
  @PrimaryGeneratedColumn({ name: 'id' })
  id: number;

  @Column({ name: 'is_active', default: true })
  active: boolean;

  @Column({
    name: 'create_timestamp',
    default: () => 'CURRENT_TIMESTAMP',
    update: false,
  })
  createTimestamp: Date;

  @Column({ name: 'created_by', update: false })
  createdBy: number;

  @Column({
    name: 'update_timestamp',
    default: () => 'CURRENT_TIMESTAMP',
    insert: false,
  })
  updateTimestamp: Date;

  @Column({ name: 'updated_by', insert: false })
  updatedBy: number;
}
