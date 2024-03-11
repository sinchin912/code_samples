import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

@Entity({ name: 'customer' })
export class User {
  @PrimaryGeneratedColumn({ name: 'id' })
  id: number;

  @Column({ name: 'username', unique: true })
  username: string;

  @Column({ name: 'userkey' })
  password: string;
}
