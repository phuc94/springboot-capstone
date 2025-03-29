import { Box, Checkbox, Collapse, Group, Stack, UnstyledButton } from '@mantine/core';
import classes from './Test.module.scss';
import { useDisclosure } from '@mantine/hooks';
import { IconChevronRight } from '@tabler/icons-react';
import { useFilterStore } from '@/store/useFilterStore';
import { useEffect } from 'react';
import { useNavigate } from '@tanstack/react-router';
import { Route } from '@/routes/test';

export function Test() {
  const [opened, { toggle }] = useDisclosure(true);
  const { fields, setField } = useFilterStore()
  const navigate = useNavigate({from: Route.fullPath})

  useEffect(() =>{
    console.log(fields)
    navigate({
      search: (prev: any) => ({page: prev.page + 1})
    })
  }, [fields])

  return (
    <Stack>
      <UnstyledButton onClick={toggle} >
        <Group justify="space-between" gap={0}>
          <Box style={{ display: 'flex', alignItems: 'center' }}>
            <Box ml="md">Category</Box>
          </Box>
            <IconChevronRight
              className={classes.chevron}
              stroke={1.5}
              size={16}
              style={{ transform: opened ? 'rotate(-90deg)' : 'none' }}
            />
        </Group>
      </UnstyledButton>
      <Collapse in={opened} >
        <Stack gap="sm" >
          <Checkbox.Group value={fields}  onChange={setField}>
            <Checkbox value="react" label="0 - 100$" />
            <Checkbox value="svelte" label="100 - 200$" description="200 items" />
          </Checkbox.Group>
        </Stack>
      </Collapse>
    </Stack>
  );
}
