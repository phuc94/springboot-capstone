import { Test } from '@/components/Test/Test'
import { createFileRoute, stripSearchParams } from '@tanstack/react-router'
import { z } from 'zod'

const defaultGameSearchParams = {
  page: 1
}

const gameSearchSchema = z.object({
  page: z.number().default(defaultGameSearchParams.page),
})

export const Route = createFileRoute('/test/')({
  component: RouteComponent,
  validateSearch: gameSearchSchema,
  search: {
    middlewares: [stripSearchParams(defaultGameSearchParams)]
  }
})

function RouteComponent() {
  return <Test />
}
