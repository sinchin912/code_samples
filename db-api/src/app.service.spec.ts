import { Test, TestingModule } from '@nestjs/testing';
import { AppService } from './app.service';
import { AppRepository } from './app.repository';

const mockAppRepository = () => ({
  listTables: jest.fn(),
  getTableHeader: jest.fn(),
  getTableRows: jest.fn(),
  getRowCount: jest.fn(),
  addData: jest.fn(),
  updateData: jest.fn(),
});

describe('AppService', () => {
  let service: AppService;
  let appRepository;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [
        AppService,
        { provide: AppRepository, useFactory: mockAppRepository },
      ],
    }).compile();

    service = module.get(AppService);
    appRepository = module.get(AppRepository);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });

  describe('listTables', () => {
    it('calls AppRespository.listTables and returns table names', async () => {
      appRepository.listTables.mockResolvedValue('someValue');
      const result = await service.listTables();
      expect(result).toEqual('someValue');
    });
  });

  describe('getTableHeader', () => {
    it('calls AppRespository.getTableHeader and returns table headers for queries table name', async () => {
      appRepository.getTableHeader.mockResolvedValue('someValue');
      const result = await service.getTableHeader('mocktable');
      expect(result).toEqual('someValue');
    });
  });
  describe('getTableData', () => {
    it('calls AppRespository.getTableHeader and returns table headers for queries table name', async () => {
      appRepository.getTableRows.mockResolvedValue('someValue');
      const result = await service.getTableData('mocktable', 1, 1);
      expect(result).toEqual('someValue');
    });
  });
  describe('getTotalRecords', () => {
    it('calls AppRespository.getRowCount and returns row count in table', async () => {
      appRepository.getRowCount.mockResolvedValue('someValue');
      const result = await service.getTotalRecords('mocktable');
      expect(result).toEqual('someValue');
    });
  });
  describe('addData', () => {
    it('calls AppRespository.addData and returns success message', async () => {
      appRepository.addData.mockResolvedValue('someValue');
      const result = await service.addData(
        'mocktable',
        'mockColumnNames',
        'mockValues',
      );
      expect(result).toEqual('someValue');
    });
  });

  describe('updateData', () => {
    it('calls AppRespository.updateData and returns success message', async () => {
      appRepository.updateData.mockResolvedValue('someValue');
      const result = await service.updateData(
        'mocktable',
        'mockValues',
        'mockCondition',
      );
      expect(result).toEqual('someValue');
    });
  });
});
