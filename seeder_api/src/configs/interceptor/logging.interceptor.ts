import {
  CallHandler,
  ExecutionContext,
  Inject,
  Injectable,
  NestInterceptor,
} from '@nestjs/common';
import { Observable, catchError, tap, throwError } from 'rxjs';
import { Logger } from 'winston';

@Injectable()
export class LoggingInterceptor implements NestInterceptor {
  constructor(
    @Inject('winston')
    private readonly logger: Logger,
  ) {}

  intercept(context: ExecutionContext, next: CallHandler): Observable<any> {
    const request = context.switchToHttp().getRequest();
    const method = request.method;
    const url = request.url;
    const now = Date.now();
    const body = JSON.stringify(request.body);

    this.logger.debug(`>> ${body}`, { param1: method, param2: url });

    return next.handle().pipe(
      tap((data) => {
        const responseTime = Date.now() - now;
        const responseData = JSON.stringify(data);
        this.logger.debug(`<<(${responseTime}ms) ${responseData}`, {
          param1: method,
          param2: url,
        });
      }),
      catchError((error) => {
        const responseTime = Date.now() - now;
        const errorData = JSON.stringify(error.message);
        this.logger.debug(`<<(${responseTime}ms) ${errorData}`, {
          param1: method,
          param2: url,
        });
        return throwError(() => error);
      }),
    );
  }
}
