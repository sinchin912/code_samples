import { Module } from '@nestjs/common';
import { AppService } from './app.service';
import { TypeOrmModule } from '@nestjs/typeorm';
import { AppRepository } from './app.repository';
import { GraphQLModule } from '@nestjs/graphql';
import { ApolloDriver, ApolloDriverConfig } from '@nestjs/apollo';
import * as path from 'path';
import { AppResolver } from './app.resolver';
import { AuthModule } from './auth/auth.module';
import { join } from 'path';
import { PassportModule } from '@nestjs/passport';

@Module({
  imports: [
    GraphQLModule.forRoot<ApolloDriverConfig>({
      driver: ApolloDriver,
      playground: true, // only for local
      typePaths: [path.join(__dirname, '../src/app.graphql')],
    }),
    TypeOrmModule.forRoot({
      type: 'postgres',
      host: 'localhost',
      port: 5432,
      username: 'postgres',
      password: 'postgres',
      database: 'postgres',
      entities: [join(__dirname, '**', '*.entity.{ts,js}')],
      synchronize: false,
      logging: true,
    }),
    AuthModule,
    PassportModule,
  ],
  providers: [AppService, AppRepository, AppResolver],
})
export class AppModule {}
