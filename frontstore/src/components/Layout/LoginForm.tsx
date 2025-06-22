import { useLogin, useRegister } from '@/hooks/auth';
import { useAuthStore } from '@/store/useAuthStore';
import { Button, Divider, Flex, Group, Stack, TextInput, Title } from '@mantine/core';
import { useForm } from '@mantine/form';
import { notifications } from '@mantine/notifications';
import { useEffect } from 'react';

const LoginForm = ({closeModal}: any) => (
   <Flex gap={40}>
    <SignInForm closeModal={closeModal}/>
      <Divider orientation="vertical" />
    <SignUpForm />
  </Flex>
)

const SignInForm = ({closeModal}: any) => {
  const {mutate: login, data, isSuccess} = useLogin();
  const authLogin = useAuthStore(state => state.login);
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

  useEffect(()=>{
    if (isSuccess && data.code == 200) {
      notifications.show({
        message: 'Đăng nhập thành công!',
        color: 'green'
      })
      authLogin(data.data.user, data.data.token);
      closeModal();
    } else if (isSuccess && data?.code !== 200) {
      notifications.show({
        title: 'Đăng nhập thất bại!',
        message: 'Nhập sai email hoặc mật khẩu.',
        color: 'red'
      })
    }
  }, [isSuccess])


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
  const {mutate: register, isSuccess} = useRegister();
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

  useEffect(()=> {
    if (isSuccess) {
      notifications.show({
        title: 'Đăng ký thành công!',
        message: 'Xin mời đăng nhập.',
        color: 'green'
      })
      form.reset();
    }
  }, [isSuccess])

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

