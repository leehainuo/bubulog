import { Social } from "@/components/ui/front/Social";
import { Avatar } from "antd";
import React from "react";

export const SideBar = () => {
  return (
    <section className="col-span-12 md:col-span-4 sticky md:top-[112px]">
      <main className="flex flex-col items-center p-4 gap-y-4 bg-white rounded-lg border h-[450px]">
        <Avatar
          size={80}
          src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQE2e6JL_PUDVEgS0U5jSNMy-x0_EdLYEI9Tg&s"
        />
        <div className="flex flex-col items-center">
          <h1 className="font-mono font-extrabold">Leehainuo</h1>
          <div className="w-full border-b my-4" />
          <p className="text-[#6f7472] text-sm text-center">✨热爱Go、Node的大二学生，为了生计学了Java.</p>
 
        </div>
        {/* 第三方联系 */}
        <Social />
      </main>
      {/* <main className="bg-white rounded-lg border">

      </main> */}
    </section>
  );
};
