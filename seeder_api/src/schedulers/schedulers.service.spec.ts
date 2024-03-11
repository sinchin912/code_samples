import { Test, TestingModule } from '@nestjs/testing';
import { SchedulersService } from './schedulers.service';

describe('SchedulersService', () => {
  let service: SchedulersService;
  let loggerDebugSpy: jest.SpyInstance;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [SchedulersService],
    }).compile();

    service = module.get<SchedulersService>(SchedulersService);

    // Mock Logger.debug method
    loggerDebugSpy = jest.spyOn(service['logger'], 'debug');
  });

  afterEach(() => {
    jest.clearAllMocks();
  });

  it('should log message when handleCron is called', () => {
    service.handleCron();
    expect(loggerDebugSpy).toHaveBeenCalledWith(
      'Called when the current second is 45',
    );
  });

  it('should log message every 10 seconds when handleInterval is called', () => {
    jest.useFakeTimers(); // Mock timers to control time
    service.handleInterval();
    jest.advanceTimersByTime(10000); // Advance time by 10 seconds
    expect(loggerDebugSpy).toHaveBeenCalledWith('Called every 10 seconds');
  });

  it('should log message once after 5 seconds when handleTimeout is called', () => {
    jest.useFakeTimers(); // Mock timers to control time
    service.handleTimeout();
    jest.advanceTimersByTime(5000); // Advance time by 5 seconds
    expect(loggerDebugSpy).toHaveBeenCalledWith('Called once after 5 seconds');
  });
});
