import { useForm, Create, useSelect } from "@refinedev/antd";

import { DatePicker, Form, Image, Input, Select, Switch } from "antd";
import { useWatch } from "antd/es/form/Form";
import TextArea from "antd/es/input/TextArea";
import { useEffect, useState } from "react";

export const CreateGame = () => {
  const { formProps, saveButtonProps, form } = useForm({
    redirect: "show",
  });
  
  const { selectProps: developerProps } = useSelect({
    resource: "developer",
    optionValue: "id",
    optionLabel: "name"
  })

  const { selectProps: esrbProps } = useSelect({
    resource: "esrb",
    optionValue: "id",
    optionLabel: "name"
  })

  const { selectProps: publisherProps } = useSelect({
    resource: "publisher",
    optionValue: "id",
    optionLabel: "name"
  })

  const { selectProps: mediaProps } = useSelect({
    resource: "media",
    optionValue: "id",
    optionLabel: "url"
  })

  const media = useWatch('media', form) || []
  const [selectedMedias, setMedias] = useState<any>([]);

  useEffect(() => {
    // console.log(mediaProps.options?.filter(item => {
    //   console.log(item)
    // }))
    setMedias(mediaProps.options?.filter(item => media?.id?.includes(item.value)))
  }, [media])
  useEffect(()=>{
    console.log(selectedMedias)
  }, [selectedMedias])

  return (
    <Create saveButtonProps={saveButtonProps}>
      <Form {...formProps} layout="vertical" >
        <Form.Item label="Title" name="title">
          <Input />
        </Form.Item>
        <Form.Item label="Price" name="price">
          <Input />
        </Form.Item>
        <Form.Item label="Description" name="description">
          <TextArea />
        </Form.Item>
        <Form.Item label="Release date" name="release_date">
          <DatePicker />
        </Form.Item>
        <Form.Item label="ESRB rating" name={["esrb_rating", "id"]}>
          <Select {...esrbProps} />
        </Form.Item>
        <Form.Item label="Developer" name={["developer", "id"]}>
          <Select {...developerProps} />
        </Form.Item>
        <Form.Item label="Publisher" name={["publisher", "id"]}>
          <Select {...publisherProps} />
        </Form.Item>
        <Form.Item label="Status" name="status">
          <Select
            options={
              Object.keys(GameStatus).map(key => {return {value: key, label: key}})
            }
          />
        </Form.Item>
        <Form.Item label="Medias" name={["media", "id"]}>
          <Select mode="multiple" {...mediaProps} />
        </Form.Item>
        { selectedMedias?.map((el: any) => (
            <Image
              key={el.title}
              width={200}
              src={el.label}
            />
          ))
        }
        <Form.Item label="Is Deleted" name="is_deleted">
          <Switch />
        </Form.Item>
        <Form.Item label="Is DLC" name="is_dlc">
          <Switch />
        </Form.Item>
      </Form>
    </Create>
  );
};

enum GameStatus {
  RELEASE = 'RELEASE'
}

