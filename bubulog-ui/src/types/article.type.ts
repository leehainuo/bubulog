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