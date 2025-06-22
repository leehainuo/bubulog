import { Header } from "@/components/layout/front/Header";

export default async function Page({
  params,
}: {
  params: Promise<{ articleId: string }>;
}) {
  const { articleId } = await params;
  return (
    <section className="w-full h-screen bg-[#f5f5f5]">
      <Header />
      <div>My Post: {articleId}</div>
    </section>
  );
}
