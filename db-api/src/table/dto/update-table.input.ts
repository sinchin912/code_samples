import { CreateTableInput } from './create-table.input';
import { PartialType } from '@nestjs/mapped-types';

export class UpdateTableInput extends PartialType(CreateTableInput) {
  id: number;
}
