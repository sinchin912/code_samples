import { Args, Mutation, Query, Resolver } from '@nestjs/graphql';
import { AppService } from './app.service';
import { Injectable, UseGuards } from '@nestjs/common';
import { JwtAuthGuard } from './auth/jwt-auth-guard';

type TableData = {
  name: string;
  value: any;
  type: string;
};

type PaginationMetadata = {
  nextPage: boolean;
  totalRecords: number;
};

@Injectable()
@Resolver()
@UseGuards(JwtAuthGuard)
export class AppResolver {
  constructor(private readonly appService: AppService) {}

  @Query('list')
  async getAllTables(): Promise<{ name: string }[]> {
    return await this.appService.listTables();
  }

  @Query('headers')
  async getHeader(
    @Args('tableName') tableName: string,
  ): Promise<{ header: string }> {
    return await this.appService.getTableHeader(tableName);
  }

  @Query('rows')
  async getTableData(
    @Args('tableName') tableName: string,
    @Args('limit', { nullable: true }) limit?: number,
    @Args('offset', { nullable: true }) offset?: number,
  ): Promise<{ data: TableData[][]; pagination: PaginationMetadata }> {
    const tableRows = await this.appService.getTableData(
      tableName,
      limit,
      offset,
    );
    const tableData: TableData[][] = [];
    if (tableRows.length > 0) {
      const columnNames = Object.keys(tableRows[0]);
      tableRows.map((tableRow) => {
        const tableRowData: TableData[] = [];
        for (const columnName of columnNames) {
          let fieldValue =
            tableRow[columnName] != null ? tableRow[columnName] : '';
          const fieldType: typeof fieldValue = typeof fieldValue;
          if (fieldType === 'object') {
            fieldValue = new Date(fieldValue).toISOString();
          }
          tableRowData.push({
            name: columnName,
            type: fieldType,
            value: fieldValue,
          });
        }
        tableData.push(tableRowData);
      });
    }

    const totalRecords = await this.appService.getTotalRecords(tableName);
    const nextPage = offset + limit < totalRecords;

    const paginationMetadata = {
      nextPage: nextPage,
      totalRecords: totalRecords,
    };
    return { data: tableData, pagination: paginationMetadata };
  }

  @Mutation()
  async addData(
    @Args('tableName') tableName: string,
    @Args('columnNames') columnNames: string,
    @Args('columnValues') columnValues: string,
  ): Promise<string> {
    return await this.appService.addData(tableName, columnNames, columnValues);
  }

  @Mutation()
  async updateData(
    @Args('tableName') tableName: string,
    @Args('updateValue') updateValue: string,
    @Args('condition') condition: string,
  ): Promise<string> {
    return await this.appService.updateData(tableName, updateValue, condition);
  }
}
