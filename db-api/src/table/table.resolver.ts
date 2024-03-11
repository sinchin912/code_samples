import { Resolver, Query, Mutation, Args } from '@nestjs/graphql';
import { TableService } from './table.service';
import { CreateTableInput } from './dto/create-table.input';
import { UpdateTableInput } from './dto/update-table.input';

@Resolver('Table')
export class TableResolver {
  constructor(private readonly tableService: TableService) {}

  @Mutation('createTable')
  create(@Args('createTableInput') createTableInput: CreateTableInput) {
    return this.tableService.create(createTableInput);
  }

  @Query('table')
  findAll() {
    return this.tableService.findAll();
  }

  @Query('table')
  findOne(@Args('id') id: number) {
    return this.tableService.findOne(id);
  }

  @Mutation('updateTable')
  update(@Args('updateTableInput') updateTableInput: UpdateTableInput) {
    return this.tableService.update(updateTableInput.id, updateTableInput);
  }

  @Mutation('removeTable')
  remove(@Args('id') id: number) {
    return this.tableService.remove(id);
  }
}
