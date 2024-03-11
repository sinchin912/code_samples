import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { DocumentBuilder, SwaggerModule } from '@nestjs/swagger';
//import { FaviconMiddleware } from './app.middleware';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);
  const config = new DocumentBuilder()
    .setTitle('DB Visualizer API')
    .setDescription('Graphql APIs for DB Visualizer')
    .setVersion('1.0')
    .build();
  const document = SwaggerModule.createDocument(app, config);
  SwaggerModule.setup('api', app, document);
  // const faviconMiddleware = new FaviconMiddleware();
  // app.use(faviconMiddleware.use.bind(faviconMiddleware));
  app.enableCors();
  await app.listen(8080);
}
bootstrap();
