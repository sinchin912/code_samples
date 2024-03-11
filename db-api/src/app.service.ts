import { Injectable } from '@nestjs/common';
import { AppRepository } from './app.repository';

@Injectable()
export class AppService {
  constructor(private readonly appRepository: AppRepository) {}

  async listTables(): Promise<{ name: string }[]> {
    return await this.appRepository.listTables();
  }

  async getTableHeader(tableName: string): Promise<{ header: string }> {
    return await this.appRepository.getTableHeader(tableName);
  }

  async getTableData(
    tableName: string,
    limit: number,
    offset: number,
  ): Promise<any[]> {
    return await this.appRepository.getTableRows(tableName, limit, offset);
  }

  async getTotalRecords(tableName: string): Promise<number> {
    return await this.appRepository.getRowCount(tableName);
  }

  async addData(
    tableName: string,
    columnNames: string,
    columnValues: string,
  ): Promise<string> {
    return await this.appRepository.addData(
      tableName,
      columnNames,
      columnValues,
    );
  }
  async updateData(
    tableName: string,
    updateValue: string,
    condition: string,
  ): Promise<string> {
    return await this.appRepository.updateData(
      tableName,
      updateValue,
      condition,
    );
  }
}
