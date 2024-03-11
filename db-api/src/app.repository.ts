import { Injectable } from '@nestjs/common';
import { EntityManager } from 'typeorm';

@Injectable()
export class AppRepository {
  constructor(private entityManager: EntityManager) {}

  // Get the list of tables
  async listTables(): Promise<{ name: string }[]> {
    const query = `SELECT table_name as name ,table_schema as schema, table_type as type FROM information_schema.tables WHERE table_schema='public'`;
    return await this.entityManager.query(query);
  }

  // Get header for each table.
  async getTableHeader(tableName: string): Promise<{ header: string }> {
    const query = `SELECT column_name as name, 
    CASE 
      WHEN data_type LIKE '%timestamp%' THEN 'date'
      WHEN data_type = 'bigint' THEN 'number'
      ELSE data_type 
    END AS type FROM information_schema.columns WHERE table_schema = 'public' AND table_name   = '${tableName}'`;
    return await this.entityManager.query(query);
  }

  // Get rows for each table.
  async getTableRows(
    //id: string,
    tableName: string,
    limit?: number,
    offset?: number,
  ): Promise<any[]> {
    let query = `SELECT * FROM public.${tableName}`;

    // Add LIMIT and OFFSET clauses conditionally
    if (limit !== undefined) {
      query += ` LIMIT ${limit}`;
      if (offset !== undefined) {
        query += ` OFFSET ${offset}`;
      }
    } else if (offset !== undefined) {
      throw new Error('Offset provided without limit');
    }
    return await this.entityManager.query(query);
  }

  // Get rows for each table.
  async getRowCount(tableName: string): Promise<number> {
    const query = `SELECT COUNT(*) FROM public.${tableName}`;
    const result = await this.entityManager.query(query);
    const count = parseInt(result[0].count, 10);
    return count;
  }

  async addData(
    tableName: string,
    columnNames: string,
    columnValues: string,
  ): Promise<string> {
    const columnName = columnNames;
    const values = columnValues;
    const query = `INSERT INTO public.${tableName} (${columnName}) VALUES(${values});`;
    await this.entityManager.query(query);
    return 'Record inserted successfully';
  }

  async updateData(
    tableName: string,
    updateValue: string,
    condition: string,
  ): Promise<string> {
    const values = updateValue;
    const queryCondition = condition;
    const query = `UPDATE public.${tableName} SET ${values} WHERE ${queryCondition};`;
    const result = await this.entityManager.query(query);
    return result[1] + ' record updated successfully';
  }
}
