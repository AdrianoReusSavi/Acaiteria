"use client"
import Link from "next/link";
import { useState } from "react";



export function NavBar() {
    const [open] = useState(true);
    const Menus = [
        { title: "Menu", src: "/", icone: "card" },
        { title: "Estoque", src: "/stock", icone: "esto" },
        { title: "Mesa", src: "/table", icone: "tabl" },
        { title: "Fechamento", src: "/cashclosing-d", icone: "day" },
        { title: "Balanceamento", src: "/cashclosing-m", icone: "month" },
    ]
    return (
        <div className="relative w-72 h-screen bg-purple-800  p-5 pt-8 duration-300">
            <nav >
                <Link href={"/"}>
                <div className="flex pl-4 items-center gap-x-4 border-b border-purple-600">
                    <img src={"../sidebar/logo.png"} />
                    <h1 className="text-white font-logo text-4xl">Point</h1>
                </div>
                </Link>
                <ul className="flex flex-col py-10 space-y-4">
                    {Menus.map((Menu, index) => (
                        <Link href={Menu.src} key={index} className="text-white">
                            <li className="flex bg-purple-700 cursor-pointer items-center gap-x-4 rounded-md p-2 text-gray-300 hover:bg-purple-600 ">
                                <img src={`../sidebar/${Menu.icone}.png`} className="mr-4 pl-2" />
                                <span className={`${!open && "hidden"} origin-left duration-200`}>{Menu.title}</span>
                            </li>
                        </Link>
                    ))}
                </ul>
            </nav>
        </div>
    )
}
