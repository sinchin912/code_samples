import { Injectable, UnauthorizedException } from '@nestjs/common';
import { PassportStrategy } from '@nestjs/passport';
import { ExtractJwt, Strategy } from 'passport-jwt';
import { UsersRepository } from '../auth/users.repository';
import { InjectRepository } from '@nestjs/typeorm';
import { JwtPayload } from './jwt-payload.interface';

@Injectable()
export class JwtStrategy extends PassportStrategy(Strategy) {
  constructor(
    @InjectRepository(UsersRepository)
    private usersRespository: UsersRepository,
  ) {
    super({
      secretOrKey: 'topSecret51',
      jwtFromRequest: ExtractJwt.fromAuthHeaderAsBearerToken(),
    });
  }
  async validate(payload: JwtPayload) {
    const { username } = payload;
    const user = await this.usersRespository.findOneBy({ username });
    if (!user) {
      throw new UnauthorizedException();
    }
    return user;
  }
}
