import { CustomerValidationPipe } from './customers.validator-pipe';
import { HttpException } from '@nestjs/common';

describe('CustomerValidationPipe', () => {
  let pipe: CustomerValidationPipe;

  beforeEach(() => {
    pipe = new CustomerValidationPipe();
  });

  it('should throw HttpException if name is missing', async () => {
    const dto = {
      name: '',
      username: 'testuser',
      userkey: 'testkey',
      roleIds: [1, 2, 3],
    };

    await expect(pipe.transform(dto)).rejects.toThrow(HttpException);
  });

  it('should throw HttpException if username is missing', async () => {
    const dto = {
      name: 'Test Name',
      userkey: 'testkey',
      username: '',
      roleIds: [1, 2, 3],
    };

    await expect(pipe.transform(dto)).rejects.toThrow(HttpException);
  });

  it('should throw HttpException if userkey is missing', async () => {
    const dto = {
      name: 'Test Name',
      username: 'testuser',
      userkey: '',
      roleIds: [1, 2, 3],
    };

    await expect(pipe.transform(dto)).rejects.toThrow(HttpException);
  });

  it('should throw HttpException if roleIds is missing', async () => {
    const dto = {
      name: 'Test Name',
      username: 'testuser',
      userkey: 'testkey',
      roleIds: [],
    };

    await expect(pipe.transform(dto)).rejects.toThrow(HttpException);
  });

  it('should return value if all fields are present', async () => {
    const dto = {
      name: 'Test Name',
      username: 'testuser',
      userkey: 'testkey',
      roleIds: [1, 2, 3],
    };

    const result = await pipe.transform(dto);

    expect(result).toEqual(dto);
  });
});
