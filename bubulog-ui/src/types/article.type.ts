export interface QueryParams {
    articleTitle: string;
    startDate?: string;
    endDate?: string;
}

export interface FormType {
    articleTitle: string,
    articleContent: string,
    articleCover: string,
    articleSummary: string,
    categoryId: string,
    tags: string[]
}

export interface Article {
    articleId: string;
    articleTitle: string;
    articleCover: string;
    articleSummary: string;
    articleContent: string;
    categoryId: string;
    tags: string[];
}