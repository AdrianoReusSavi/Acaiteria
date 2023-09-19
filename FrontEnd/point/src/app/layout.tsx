import { NavBar } from '@/components/Navbartb/navbar';
import './globals.css';

export const metadata = {
  title: 'Point',
  description: 'Sistema para facilitar as vendas em restaurante',
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en">
      <body>

        <div className="flex">
          <NavBar />
          {children}
        </div>

      </body>
    </html>
  );
}
