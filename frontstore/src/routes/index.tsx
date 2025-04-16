import { createFileRoute } from '@tanstack/react-router'
import AppCarousel from '@/components/Carousel';
import Platform from '@/components/Platform';

export const Route = createFileRoute('/')({
  component: RouteComponent,
})

export function RouteComponent() {
  return (
    <>
      <AppCarousel />
      <Platform />
      <Platform />
      <Platform />
      <Platform />
    </>
  );
}

