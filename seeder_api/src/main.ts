import { NestFactory } from '@nestjs/core';
import { SwaggerModule, DocumentBuilder } from '@nestjs/swagger';
import { AppModule } from './app.module';
import { AuthMiddleware } from './configs/middleware/auth.middleware';
import { AuthService } from './configs/auth.service';
import {
  DbExceptionFilter,
  HttpExceptionFilter,
} from './configs/filter/http-exception.filter';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);
  const config = new DocumentBuilder()
    .setTitle('Seeder APi')
    .setDescription('APIs for seeder application')
    .setVersion('1.0')
    .build();
  const document = SwaggerModule.createDocument(app, config);
  SwaggerModule.setup('api', app, document);

  const authService = app.get(AuthService);
  const authMiddleware = new AuthMiddleware(authService);
  app.use(authMiddleware.use.bind(authMiddleware));
  app.useGlobalFilters(new HttpExceptionFilter(), new DbExceptionFilter());
  app.enableShutdownHooks();
  await app.listen(3000);
}
bootstrap();
