import { Header } from "@/components/layout/front/Header";
import { AuthCheck } from "@/components/ui/Auth/AuthCheck";

const Home = async () => {
  return (
    <section>
      <AuthCheck />
      <Header />
      <main className="flex justify-center items-center h-[100vh]">
        Hello Next!
      </main>
    </section>
  );
}

export default Home