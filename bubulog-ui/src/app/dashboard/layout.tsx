import { SidebarProvider } from "@/components/ui/sidebar";
import { Slider } from "@/components/layout/back/Slider";
import { Header } from "@/components/layout/back/Header";

export default function DashboardLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <SidebarProvider>
      <Slider />
      <main className="w-full">
        <Header />
        {children}
      </main>
    </SidebarProvider>
  );
}
