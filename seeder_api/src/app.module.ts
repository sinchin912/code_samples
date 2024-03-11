import { Module } from '@nestjs/common';
import { ConfigModule } from '@nestjs/config';
import { CustomersModule } from './customers/customers.module';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Customer } from './customers/entities/customer.entity';
import { RolesModule } from './roles/roles.module';
import { Role } from './roles/entities/role.entity';
import { CommonsModule } from './commons/commons.module';
import { ConfigsModule } from './configs/configs.module';
import { WinstonModule } from 'nest-winston';
import * as winston from 'winston';
import * as path from 'path';
import { APP_INTERCEPTOR } from '@nestjs/core';
import { LoggingInterceptor } from './configs/interceptor/logging.interceptor';
import { SchedulersModule } from './schedulers/schedulers.module';
import { ScheduleModule } from '@nestjs/schedule';

const customFormat = winston.format.printf(
  ({ level, message, timestamp, metadata }) => {
    const { param1, param2 } = metadata;
    const localTimestamp = new Date(timestamp).toLocaleString(undefined, {
      timeZone: 'Asia/Kolkata',
    });
    return `${localTimestamp} ${level.toUpperCase()} ${param1}:${param2} - ${message}`;
  },
);

@Module({
  imports: [
    ConfigModule.forRoot({
      envFilePath: '.local.env',
    }),
    TypeOrmModule.forRoot({
      type: 'mysql',
      host: 'localhost',
      port: 3306,
      username: 'root',
      password: 'password',
      database: 'SEEDER',
      entities: [Customer, Role],
      synchronize: false,
      logging: true,
    }),
    WinstonModule.forRoot({
      format: winston.format.combine(
        winston.format.timestamp(),
        winston.format.metadata({
          fillExcept: ['message', 'level', 'timestamp'],
        }),
        customFormat,
      ),
      transports: [
        new winston.transports.Console(),
        new winston.transports.File({
          dirname: path.join(__dirname, '../log/'),
          filename: 'seeder_api.log',
          level: 'debug',
        }),
      ],
    }),

    CustomersModule,

    RolesModule,

    CommonsModule,

    ConfigsModule,

    ScheduleModule.forRoot(),

    SchedulersModule,
  ],
  providers: [
    {
      provide: APP_INTERCEPTOR,
      useClass: LoggingInterceptor,
    },
  ],
})
export class AppModule {}
