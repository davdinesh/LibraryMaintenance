import { SubjectModel } from './subject.model';
export class Book {
    id: number;
    title: string;
    price: number;
    volume: number;
    publishedDate: Date;
    subject: SubjectModel;
}