import { useLogin, useRegister } from '@/hooks/user';
import { Button, Divider, Flex, Group, Stack, TextInput, Title } from '@mantine/core';
import { useForm } from '@mantine/form';

const LoginForm = () => (
  <Flex gap={40}>
    <SignInForm />
      <Divider orientation="vertical" />
    <SignUpForm />
  </Flex>
)

const SignInForm = () => {
  const {mutate: login} = useLogin();
  const form = useForm({
    mode: 'uncontrolled',
    initialValues: {
      email: '',
      password: '',
    },

    validate: {
      email: (value) => (/^\S+@\S+$/.test(value) ? null : 'Invalid email'),
    },
  });

  return (
    <form onSubmit={form.onSubmit((values) => login(values))}>
      <Stack>
        <Title order={2}>Đăng nhập</Title>
        <TextInput
          size="lg"
          withAsterisk
          label="Địa chỉ email"
          placeholder="nguyenvana@email.com"
          key={form.key('email')}
          {...form.getInputProps('email')}
        />
        <TextInput
          size="lg"
          withAsterisk
          label="Mật khẩu"
          placeholder="**********"
          type="password"
          key={form.key('password')}
          {...form.getInputProps('password')}
        />

        <Group justify="flex-end" mt="md">
          <Button color="lime" type="submit">Đăng nhập</Button>
        </Group>

      </Stack>
    </form>
  )
}

const SignUpForm = () => {
  const {mutate: register} = useRegister();
  const form = useForm({
    mode: 'uncontrolled',
    initialValues: {
      email: '',
      password: '',
      name: ''
    },

    validate: {
      email: (value) => (/^\S+@\S+$/.test(value) ? null : 'Invalid email'),
    },
  });

  return (
    <form onSubmit={form.onSubmit((values) => register(values))}>
      <Stack>
        <Title order={2}>Đăng ký</Title>
        <TextInput
          size="lg"
          withAsterisk
          label="Tên"
          placeholder="Nguyễn Văn A"
          key={form.key('name')}
          {...form.getInputProps('name')}
        />
        <TextInput
          size="lg"
          withAsterisk
          label="Địa chỉ email"
          placeholder="nguyenvana@email.com"
          key={form.key('email')}
          {...form.getInputProps('email')}
        />
        <TextInput
          size="lg"
          withAsterisk
          label="Mật khẩu"
          placeholder="**********"
          type="password"
          key={form.key('password')}
          {...form.getInputProps('password')}
        />

        <Group justify="flex-end" mt="md">
          <Button color="indigo" type="submit">Đăng ký</Button>
        </Group>
      </Stack>
    </form>
  )
}

export default LoginForm;

