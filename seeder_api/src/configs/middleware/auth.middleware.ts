import {
  HttpException,
  HttpStatus,
  Injectable,
  NestMiddleware,
} from '@nestjs/common';
import { AuthService } from '../auth.service';
import { Response, NextFunction } from 'express';
import { AuthenticatedRequest } from 'src/commons/interfaces/authenticate-request.interface';

@Injectable()
export class AuthMiddleware implements NestMiddleware {
  constructor(private readonly authService: AuthService) {}

  async use(req: AuthenticatedRequest, res: Response, next: NextFunction) {
    try {
      const authHeader = req.headers['authorization'];
      if (!authHeader) {
        throw new HttpException(
          'Auth Header not present',
          HttpStatus.FORBIDDEN,
        );
      }

      if (authHeader.startsWith('Basic ')) {
        const base64Credentials = authHeader.slice('Basic '.length);
        const credentials = Buffer.from(base64Credentials, 'base64').toString(
          'utf-8',
        );
        const [username, password] = credentials.split(':');

        const loggedUser = await this.authService.signIn(username, password);
        req.user = loggedUser;
        next();
      } else {
        throw new HttpException('Invalid Auth Header', HttpStatus.FORBIDDEN);
      }
    } catch (error) {
      next(error);
    }
  }
}
